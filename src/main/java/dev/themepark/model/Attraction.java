package dev.themepark.model;

import lombok.*;

import java.util.ArrayDeque;
import java.util.Deque;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"junkWaitingQueue","blackCowWaitingQueue"})
public class Attraction {
    private int id;
    private String name;
    private int capacity;
    private boolean isActive;

    @Builder.Default
    private Deque<Person> junkWaitingQueue = new ArrayDeque<>();
    @Builder.Default
    private Deque<Person> blackCowWaitingQueue = new ArrayDeque<>();

    public Attraction(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public void addJunk(Person person) {
        junkWaitingQueue.add(person);
    }

    public void addBlackCow(Person person) {
        blackCowWaitingQueue.add(person);
    }

    public synchronized void board(){
        while(!isActive){
            int count=capacity;
            isActive= true;
            for (int i = 0; i < count; i++) {
                if(blackCowWaitingQueue.isEmpty()){
                    break;
                }
                blackCowWaitingQueue.pop();
                count--;
            }
            System.out.println(name+"기구에 "+(capacity-count)+ "명의 매직패스 인원이 입장하였습니다" );
            int t= count;
            for (int i = 0; i < count; i++) {
                if(junkWaitingQueue.isEmpty()){
                    break;
                }
                junkWaitingQueue.pop();
                t--;
            }
            System.out.print(name+"기구에 ");
            System.out.println(t==0? +capacity+"명 모두 탑승 완료": t+"만큼 빈 자리로 출발하였습니다 ");
            if (junkWaitingQueue.isEmpty() && blackCowWaitingQueue.isEmpty()){
                System.out.println(name+"기구의 대기 인원이 없습니다");
                isActive = false;
                break;
            }
            isActive = false;
        }
    }
}

//놀이기구: id, 이름, 최대 탑승 인원, 대기열(list), 매직패스 대기열