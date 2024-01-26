package com.sist.commons;
import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 *    AOP : 공통 모듈 (공통으로 사용되는 소스를 모아서 관리)
 *          유지 보수, 재사용
 *          ==> OOP에서 불가능한것을 추가해서 보완
 *              ===
 *               |자동호출(Callback) => 불가능
 *          ==> Callback = 원하는 위치를 지정하면 호출이 가능하도록
 *                         ========
 *                         1. 메소드 지정 (지정된 메소드 => Target)
 *                         2. 메소드의 위치 (JoinPoint)
 *                            public void display()
 *                            {
 *                               @Before
 *                               try
 *                               
 *                            }
 *     => PointCut
 *     => JoinPoint
 *     => Weaving
 *     => Target
 *     => Proxy
 *     => Advice
 */
@ControllerAdvice
public class CommonsException {

}
