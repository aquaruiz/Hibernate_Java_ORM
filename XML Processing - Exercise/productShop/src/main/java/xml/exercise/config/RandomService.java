package xml.config;

import java.util.Random;

public class RandomService {
    public static Integer getRandomInt(int minNum, int maxNum) {
        Random random = new Random();

        int num = random.nextInt((maxNum - minNum) + 1) + minNum;
//        int tries = random.nextInt((5 - 0) + 1) + 0;
//
//        for(int i = 0; i < tries; i++){
//            if (Math.sqrt(num) == (int)Math.sqrt(num)){
//                friends.add(users[num]);
//            }

        return num;
    }
}
