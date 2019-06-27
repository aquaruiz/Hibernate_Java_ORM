import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;

import entities.Diagnose;
import entities.Medicament;
import entities.Patient;
import entities.Visitation;

public class Engine implements Runnable{
	private final EntityManager entityManager;
	
	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void run() {
		this.entityManager.getTransaction().begin();

		Medicament medicament = new Medicament();
        medicament.setName("Aspirine");
        medicament.setPatients(new HashSet<>());

        Medicament medicament2 = new Medicament();
        medicament2.setName("Vitamine C");
        medicament2.setPatients(new HashSet<>());

        Diagnose diagnose = new Diagnose();
        diagnose.setName("Insomnia");
        diagnose.setComments("Harmfull");
        diagnose.setPatients(new HashSet<>());

        Diagnose diagnose2 = new Diagnose();
        diagnose2.setName("Flu");
        diagnose2.setComments("Harmless");
        diagnose2.setPatients(new HashSet<>());

        Visitation visitation = new Visitation();
        visitation.setDate(new Date());
        visitation.setComments("just passing by");

        Visitation visitation2 = new Visitation();
        visitation2.setDate(new Date());
        visitation2.setComments("Who wants some beer?");

        Visitation visitation3 = new Visitation();
        visitation3.setDate(new Date());
        visitation3.setComments("Let's party!");

        Patient patient = new Patient();
        patient.setFirstName("Pesho");
        patient.setLastName("Peshev");
        patient.setDateOfBirth(new Date());
        patient.setAddress("In the middle of nowhere");
        patient.setHasMedicalInsurance(true);
        patient.setEmail("peshev@mail.bg");
        patient.setDiagnoses(new HashSet<Diagnose>());
        patient.getDiagnoses().add(diagnose);
        patient.getDiagnoses().add(diagnose2);
        
        patient.setMedicaments(new HashSet<Medicament>());
        patient.getMedicaments().add(medicament);
        patient.getMedicaments().add(medicament2);
        
        patient.setVisitations(new HashSet<Visitation>());
        patient.getVisitations().add(visitation);
        patient.getVisitations().add(visitation2);
        patient.getVisitations().add(visitation3);

        Patient patient2 = new Patient();
        patient2.setFirstName("Gosho");
        patient2.setLastName("Goshev");
        patient2.setDateOfBirth(new Date());
    	patient2.setAddress("na selo");
        patient2.setHasMedicalInsurance(false);
        patient2.setEmail("G@gbg.bg");
        patient2.setDiagnoses(new HashSet<Diagnose>());
        patient2.getDiagnoses().add(diagnose);
        patient2.getDiagnoses().add(diagnose2);

        patient2.setMedicaments(new HashSet<Medicament>());
        patient2.getMedicaments().add(medicament);
        patient2.getMedicaments().add(medicament2);
        patient2.setVisitations(new HashSet<Visitation>());
        patient2.getVisitations().add(visitation3);

        diagnose.getPatients().add(patient);
        diagnose.getPatients().add(patient2);
        diagnose2.getPatients().add(patient);
        diagnose2.getPatients().add(patient2);

        medicament.getPatients().add(patient);
        medicament.getPatients().add(patient2);
        medicament2.getPatients().add(patient);
        medicament2.getPatients().add(patient2);

        visitation.setPatient(patient);
        visitation2.setPatient(patient);
        visitation3.setPatient(patient2);

        entityManager.persist(patient);
        entityManager.persist(patient2);		
		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}
}