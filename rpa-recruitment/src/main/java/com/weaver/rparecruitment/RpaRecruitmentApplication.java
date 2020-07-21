package com.weaver.rparecruitment;

import com.weaver.rparecruitment.controller.ExecutorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class RpaRecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpaRecruitmentApplication.class, args);
	}

	/**
	 * CommandLineRunner和ApplicationRunner
	 * Spring容器初始化完毕之后执行起run方法
	 */
	@Component
	public class ApplicationRunImpl implements ApplicationRunner {
		private  ExecutorController executor;

		@Autowired
		public ApplicationRunImpl(ExecutorController executor){
			this.executor = executor;
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			executor.downLoadResumeForExMail();
		}
	}

}
