package com.planetsystems.monitoring.server;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.server.spring.HandlerModule;
import com.gwtplatform.dispatch.server.spring.actionvalidator.DefaultActionValidator;
import com.gwtplatform.dispatch.server.spring.configuration.DefaultModule;
import com.planetsystems.monitoring.server.dispatch.admin.accounts.AccountsHandler;
import com.planetsystems.monitoring.server.dispatch.admin.organs.OrganizationalUnitsHandler;
import com.planetsystems.monitoring.server.dispatch.users.ComfirmPasswordHandler;
import com.planetsystems.monitoring.server.dispatch.users.UsersHandler;
import com.planetsystems.monitoring.server.dispatcher.administration.AuditTrailHandler;
import com.planetsystems.monitoring.server.dispatcher.administration.CurrencyHandler;
import com.planetsystems.monitoring.shared.MonitoringAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AccountsAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.AuditTrailAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.CurrencyAction;
import com.planetsystems.monitoring.shared.dispatch.admin.organs.OrganizationalUnitsAction;
import com.planetsystems.monitoring.shared.dispatch.users.ConfirmPasswordAction;
import com.planetsystems.monitoring.shared.dispatch.users.UsersAction;

@Configuration
@Import(DefaultModule.class)
public class ServerModule extends HandlerModule {
	// Required
	public ServerModule() {

	}

	// Required
	@Bean
	public ActionValidator getDefaultActionValidator() {
		return new DefaultActionValidator();
	}

	
	// Users
		@Bean
		public UsersHandler getAddUsersHandler() {

			return new UsersHandler();
		}

		@Bean
		public CurrencyHandler getCurrencyHandler() {

			return new CurrencyHandler();
		}

		@Bean
		public AccountsHandler AccountsHandler() {

			return new AccountsHandler();
		}

		@Bean
		public OrganizationalUnitsHandler OrganizationalUnitsHandler() {

			return new OrganizationalUnitsHandler();
		}



		@Bean
		public AuditTrailHandler AuditTrailHandler() {

			return new AuditTrailHandler();
		}

	

		@Bean
		public ComfirmPasswordHandler getComfirmPasswordHandler() {
			return new ComfirmPasswordHandler();
		}

	
	@Override
	protected void configureHandlers() {

		bindHandler(MonitoringAction.class, MonitoringActionHandler.class);
		
		bindHandler(UsersAction.class, UsersHandler.class);

		bindHandler(CurrencyAction.class, CurrencyHandler.class);

		bindHandler(AccountsAction.class, AccountsHandler.class);

		bindHandler(OrganizationalUnitsAction.class, OrganizationalUnitsHandler.class);

		bindHandler(AuditTrailAction.class, AuditTrailHandler.class);


		bindHandler(ConfirmPasswordAction.class, ComfirmPasswordHandler.class);


	}

    @Bean
	public MonitoringActionHandler getSchoolRegisterActionHandler() {
		return new MonitoringActionHandler();
	}

}
