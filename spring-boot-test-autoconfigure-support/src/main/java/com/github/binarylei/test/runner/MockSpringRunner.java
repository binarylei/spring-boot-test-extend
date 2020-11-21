package com.github.binarylei.test.runner;

import lombok.extern.slf4j.Slf4j;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author binarylei
 * @version 2020-11-21
 */
public class MockSpringRunner extends SpringJUnit4ClassRunner {
    public MockSpringRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected TestContextManager createTestContextManager(Class<?> clazz) {
        return new AutowiredTestContextManager(clazz);
    }

    @Slf4j
    public static class AutowiredTestContextManager extends TestContextManager {
        public AutowiredTestContextManager(Class<?> testClass) {
            super(testClass);
        }

        @Override
        public void registerTestExecutionListeners(TestExecutionListener... testExecutionListeners) {
            for (TestExecutionListener listener : testExecutionListeners) {
                if (listener instanceof DependencyInjectionTestExecutionListener) {
                    listener = new MockDependencyInjectionTestExecutionListener();
                }

                if (log.isTraceEnabled()) {
                    log.trace("Registering TestExecutionListener: " + listener);
                }
                super.registerTestExecutionListeners(listener);
            }
        }
    }

}
