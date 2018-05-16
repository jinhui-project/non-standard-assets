package com.jinhui.wechat;

/**
 * key-value来表示每一个参数对象,
 * key: 参数名, value: 参数值
 */
public interface Parameter<V> {
    String key();
    V value();

    class ParameterImpl<V> implements Parameter{
        String key;
        V value;
        public ParameterImpl(String key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String key() {
            return this.key;
        }
        @Override
        public V value() {
            return this.value;
        }

        @Override
        public String toString() {
            return "ParameterImpl{" +
                    "key='" + key + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

}
