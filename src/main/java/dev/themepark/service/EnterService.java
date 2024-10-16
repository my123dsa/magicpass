package dev.themepark.service;


import dev.themepark.model.Attraction;
import dev.themepark.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class EnterService {

    private final List<Attraction> attractions;
    private final List<Person> people;
    private final ExecutorService executorService;

    public EnterService() {
        this.attractions = new ArrayList<>();
        this.people = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(20);
    }

    public void run() throws InterruptedException {

        init();
        inputWaiting();

        attractions.get(0).board();
        attractions.get(1).board();
        attractions.get(2).board();
    }

    private void inputWaiting() throws InterruptedException {
        int threadCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i=0;i<threadCount;i++){
            int k=i;
            Person tempPerson= people.get(i);
            executorService.submit(() -> {
                try{
                    if(tempPerson.isBlackCow()){
                        attractions.get(k % 3).addBlackCow(tempPerson);
//                    System.out.println(tempPerson+"사람이 탑승");
                    }
                    else{
                        attractions.get(k % 3).addJunk(tempPerson);
//                    System.out.println(tempPerson+"사람이 탑승");
                    }
                }finally{
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

    private  void init(){
        for (int i=0;i<100;i++){
            people.add(Person.builder()
                    .id(i)
                    .name(koreanNames[i])
                    .isBlackCow(i%7==0)
                    .build());

        }
        attractions.add(Attraction.builder()
                .id(1)
                .name("바이킹")
                .capacity(20)
                .isActive(false)
                .build());
        attractions.add(Attraction.builder()
                .id(2)
                .name("롤러 코스터")
                .capacity(10)
                .isActive(false)
                .build());
        attractions.add(Attraction.builder()
                .id(3)
                .name("다람쥐 통통")
                .capacity(12)
                .isActive(false)
                .build());
    }

    String[] koreanNames = {
            "김민준", "이서준", "박지훈", "최지우", "정현우", "강예준", "조유진", "문서현", "유정민", "윤도윤",
            "한수빈", "송지호", "오다은", "신승우", "고지원", "서지민", "정수현", "백서윤", "임예진", "안시우",
            "이하준", "최수빈", "백지우", "조승현", "강민서", "권유진", "김나연", "문수빈", "배지우", "신현우",
            "박다빈", "최태민", "조준서", "류서진", "강현수", "임다은", "송서윤", "장수민", "홍지후", "정다은",
            "김현서", "이유진", "조다빈", "문현우", "장예준", "강태민", "윤지원", "한민재", "백민준", "유지후",
            "박예진", "정서윤", "김다은", "오지호", "신지우", "이수빈", "최다윤", "정지후", "서준서", "조수현",
            "박태민", "강도윤", "문지민", "류현우", "이현수", "윤예진", "배태윤", "오수현", "신민준", "조현우",
            "서유진", "김준서", "최서윤", "한다빈", "문유진", "임지민", "정다빈", "윤지호", "조태민", "박서준",
            "김다윤", "이태민", "박도윤", "서하준", "송지훈", "류민준", "장도윤", "이수현", "최도윤", "한서진",
            "박지원", "김태민", "신유진", "백다윤", "조다은", "정도윤", "문지후", "김지후", "윤서진", "류태민"
    };

}
