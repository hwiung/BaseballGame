package com.example.numplay;

import java.util.*;

public class BaseballGame {
    private List<Integer> answer; // 정답 숫자를 저장할 리스트
    private Scanner scanner;

    // 객체 생성시 정답을 만들도록 함
    public BaseballGame() {
        this.answer = generateAnswer();
        this.scanner = new Scanner(System.in);
    }

    // 게임을 시작하는 메서드
    public void play() {
        System.out.println("< 게임을 시작합니다 >");

        while (true) {
            System.out.println("숫자를 입력하세요: ");
            String input = scanner.nextLine();
            // 1. 유저에게 입력값을 받음
            // 2. 올바른 입력값을 받았는지 검증
            // 3. 게임 진행횟수 증가
            // 4. 스트라이크 개수 계산
            // 5. 정답여부 확인, 만약 정답이면 break 를 이용해 반복문 탈출
            // 6. 볼 개수 계산
            // 7. 힌트 출력

         // 입력값 검증
        if (!validateInput(input)){
            System.out.println("올바르지 않은 입력값입니다.");
            continue;
        }
        // 스트라이크와 볼 개수 계산
        int strikes = countStrike(input); // 스트라이크 수 계산
        int balls = countBall(input);     // 볼 수 계산

        // 힌트 출력
        displayHint(strikes, balls);

        // 3스트라이크일 경우 게임 종료
        if (strikes == 3) {
            System.out.println("정답입니다!");
            break;
        }
    }
}

    // 정답 숫자 생성 메서드
    private List<Integer> generateAnswer() {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        // 중복되지 않는 3자리 숫자를 생성
        while (set.size() < 3) {
            int num = random.nextInt(9) + 1; // 1부터 9까지의 숫자
            set.add(num);
        }

        List<Integer> answerList = new ArrayList<>(set);
        Collections.shuffle(answerList); // 리스트를 섞음

        return answerList;
    }

    // 입력값 검증 메서드
    protected boolean validateInput(String input) {
        if (input.length() != 3) return false;

        Set<Character> uniqueDigits = new HashSet<>();
        for (char c: input.toCharArray()){
        if (!Character.isDigit(c) || c == '0') return false;
        uniqueDigits.add(c);
    }

    return uniqueDigits.size() == 3; // 중복 여부 확인
}

    // 스트라이크 개수 계산 메서드
    private int countStrike(String input) {
        int strikes = 0;
        for(int i = 0; i < 3; i++) {
            if (input.charAt(i) - '0' == answer.get(i)) {
                strikes++;
            }
        }
        return strikes;
    }

    // 볼 개수 계산 메서드
    private int countBall(String input) {
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            int num = input.charAt(i) - '0';
            if (answer.contains(num) && answer.indexOf(num) != i) {
                balls++;
            }
        }
        return balls;
    }

    // 힌트 출력 메서드
    private void displayHint(int strike, int balls) {
        if (strike == 0 && balls == 0) {
            System.out.println("아웃");
        } else {
            System.out.println(strike + " 스트라이크 " + balls + " 볼");
        }
    }
}
