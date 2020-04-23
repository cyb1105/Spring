package myspring.aop.xml;
import org.aspectj.lang.ProceedingJoinPoint;

//Around Advice
public class PerformanceTraceAdvice {
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		//타겟 메서드의 signature 정보
		String targetMethodName = joinPoint.getSignature().getName();		
		System.out.println(targetMethodName + " 시작");
		//타겟메서드의 Argument
		for(Object arg : joinPoint.getArgs()) {
			System.out.println(targetMethodName+"의 아규먼트:"+arg);
		}
		
		//타겟의 메서드가 호출되기 전의 시간 
		long start = System.currentTimeMillis();		
		try {
			//new Object[] {new String("dooly")}
			//intercepted된 타겟의 메서드 호출
			Object result = joinPoint.proceed();
			return result;
		} finally {
			//타겟의 메서드가 호출된 후의 시간
			long finish = System.currentTimeMillis();
			System.out.println(targetMethodName + " 종료");
			System.out.println(targetMethodName + " 실행 시간 : " + 
			   (finish - start) + " ms");
		}
	}
}
