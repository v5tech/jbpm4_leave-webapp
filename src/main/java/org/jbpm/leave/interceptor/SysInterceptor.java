package org.jbpm.leave.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class SysInterceptor extends MethodFilterInterceptor {
	@Override
	public void destroy() {

	}

	@Override
	public void init() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public String doIntercept(ActionInvocation invoke) throws Exception {
		if (null == ActionContext.getContext().getSession().get("user")) {
			return Action.LOGIN;
		} else {
			return invoke.invoke();
		}
	}
}
