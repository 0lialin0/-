package cn.wtkj.charge_inspect.mvp;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static cn.wtkj.charge_inspect.mvp.Defaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Dynamically proxy to generate a new object instance for a given class by using reflections
 */
final class NoOp {

    private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

    private NoOp() {
        // no instances
    }

    @SuppressWarnings("unchecked")
    public static <T> T of(Class<T> interfaceClass) {
        Log.i("TAG", DEFAULT_VALUE.getClass().getSimpleName());
        return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                DEFAULT_VALUE);
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return defaultValue(method.getReturnType());
        }
    }
}