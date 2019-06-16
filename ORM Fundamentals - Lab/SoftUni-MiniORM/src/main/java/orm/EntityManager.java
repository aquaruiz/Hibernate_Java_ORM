package orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import orm.annotations.Column;
import orm.annotations.Id;

public class EntityManager<E> implements DbContext<E> {
	private static final String INSERT_STATEMENT =  "INSERT INTO `%s` (%s) VALUES (%s);";
	private static final String UPDATE_STATEMENT = "UPDATE %s SET %s WHERE %s;";
	private static final String SELECT_FIRST_WITH_WHERE = "SELECT * FROM %s WHERE 1 %s LIMIT 1;";
	private static final String SELECT_ALL_WITH_WHERE = "SELECT * FROM %s WHERE 1 %s;";
	private Connection connection;
	
	public EntityManager(Connection connection) {
		this.connection = connection;
	}

	public boolean persist(E entity) throws IllegalAccessException, SQLException{
		Field idField = this.getId(entity.getClass());
		idField.setAccessible(true);

		Object idValue = idField.get(entity);
				
		if (idValue == null || (int) idValue <= 0)  {
			return this.doInsert(entity, idField);
		}
		
		return this.doUpdate(entity, idField);
	}

	private boolean doInsert(E entity, Field idField) throws SQLException {
		String tableName = this.getTableName(entity.getClass());
		
		String[] tableFields = this.getTableFields(entity);
		String[] tableValues = this.getTableValues(entity);
		
		String query = String.format(INSERT_STATEMENT,
				tableName,
				String.join(", ", tableFields),
				String.join(", ", tableValues));
		
		return this.connection.prepareStatement(query).execute();
	}
	
	private boolean doUpdate(E entity, Field idField) throws SQLException, IllegalArgumentException, IllegalAccessException {
		String tableName = this.getTableName(entity.getClass());
		
		String[] tableFields = this.getTableFields(entity);
		String[] tableValues = this.getTableValues(entity);

		String[] fieldValuePairs = IntStream.range(0, Math.min(tableFields.length, tableValues.length))
				.mapToObj(i -> "`" + tableFields[i] + "`=" + tableValues[i])
				.toArray(String[]::new);
				
		String whereId = "`" + idField.getName() + "`='" + idField.get(entity) + "'";
				
		String query = String.format(UPDATE_STATEMENT,
				tableName,
				String.join(", ", fieldValuePairs),
				whereId);
		
		return this.connection.prepareStatement(query).execute();
	}
	
	private String[] getTableValues(E entity) {
		return Arrays.stream(entity.getClass()
				.getDeclaredFields())
				.filter(e -> e.isAnnotationPresent(Column.class))
				.map(f -> {
					f.setAccessible(true);
					Object value;
					try {
						value = f.get(entity);
						return "'" + value.toString() + "'";
					} catch (IllegalArgumentException | IllegalAccessException e1) {
						e1.printStackTrace();
						return "";
					}
				})
				.toArray(String[]::new);
	}

	private String[] getTableFields(E entity) {
		return Arrays.stream(entity.getClass()
				.getDeclaredFields())
				.filter(e -> e.isAnnotationPresent(Column.class))
				.map(f -> {
					f.setAccessible(true);
					return f.getName();
				})
				.toArray(String[]::new);
	}

	private String getTableName(Class aClass) {
		return aClass.getSimpleName().toLowerCase().concat("s");
	}

	public Iterable<E> find(Class<E> table) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		return this.find(table, null);
	}

	public Iterable<E> find(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String tableName = this.getTableName(table);
		
		String query = String.format(SELECT_ALL_WITH_WHERE,
				tableName,
				where == null ? "" : "AND " + where);
		Statement statement = this.connection.createStatement();
		
		ResultSet rs = statement.executeQuery(query);
		
		List<E> result = new ArrayList<>();
		while (rs.next()) {
			E current = this.mapResultToEntity(rs, table);
			result.add(current);
		}

		return result;
	}

	public E findFirst(Class<E> table) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return this.findFirst(table, null);
	}

	public E findFirst(Class<E> table, String where) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return this.find(table, where).iterator().next();
	}
	
	private E mapResultToEntity(ResultSet rs, Class<E> table) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		E entity = table.getDeclaredConstructor().newInstance();
		Arrays.stream(table.getDeclaredFields())
			.forEach(f -> {
				f.setAccessible(true);
				String name = f.getName();
				Object value = null;
				try {
					value = this.getValueFromResultSet(rs, name, f.getType());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					f.set(entity, value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		return entity;
	}

	private Object getValueFromResultSet(ResultSet rs, String name, Class<?> type) throws SQLException {
		Object result = null;
		
		if (type == int.class) {
			result = rs.getInt(name);
		} else if (type == String.class) {
			 result = rs.getString(name);
		} else if (type == Date.class) {
			result = rs.getDate(name);
		}
		
		return result;
	}

	private Field getId(Class entity) {
		return Arrays.stream(
				entity
				.getDeclaredFields()
			).filter(e -> e.isAnnotationPresent(Id.class))
				.findFirst()
				.orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key."));
	}
}