package org.module.selfRibbon.config;

import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.IRule;

/**
 * @author Administrator
 *
 */
public class SelfRibbonPolicy {
	@Bean
	public IRule ribbonRule() { // 其中IRule就是所有规则的标准
		return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
	}
}
