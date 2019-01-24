package com.tdl.devist.model;

/**
 * Presentation layer단에서 보여주기 위한 로직. 주로 Thymeleaf에서 호출된다.
 */
// NOTE: 원래는 DTO를 이용해서 view단 로직을 만드는 것이 바람직하다고 여겨지나, 인터페이스로 나눠봤음 - delf
// NOTE: 바람직하지 않다고 생각하여 보류 중
public interface Presentable {
    /**
     * @return checkbox에 필요한 boolean[]를 반환한다.
     */
    // boolean[] getBooleanArr();

    /**
     * @return 달력 표시에 필요한 array를 반환
     */
    // NOTE: 지금은 필요 없어보임. 나중에 week기준이 아닌 month가 추가되었을 때 사용되지 않을까 싶음. 위에 달력이라고 표현했는데 그것도 예중 하나. - delf
    // byte[] getArr() throws NotImplementedException;

    /**
     * 각 RepeatDay가 가지고 있는 "반복일"에 대한 변수를 초기화한다.
     * @param checkbox presentation단에서 입력받는 checkbox에 해당하는 boolean[].
     */
    // void setFixedDays(boolean[] checkbox);
}
