package com.example.agentscope.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Agent 配置属性
 */
@Component
@ConfigurationProperties(prefix = "agent")
public class AgentProperties {

    private ReactConfig react = new ReactConfig();
    private MultiConfig multi = new MultiConfig();

    public ReactConfig getReact() {
        return react;
    }

    public void setReact(ReactConfig react) {
        this.react = react;
    }

    public MultiConfig getMulti() {
        return multi;
    }

    public void setMulti(MultiConfig multi) {
        this.multi = multi;
    }

    public static class ReactConfig {
        private int maxIterations = 10;

        public int getMaxIterations() {
            return maxIterations;
        }

        public void setMaxIterations(int maxIterations) {
            this.maxIterations = maxIterations;
        }
    }

    public static class MultiConfig {
        private int maxIterations = 8;

        public int getMaxIterations() {
            return maxIterations;
        }

        public void setMaxIterations(int maxIterations) {
            this.maxIterations = maxIterations;
        }
    }
}
