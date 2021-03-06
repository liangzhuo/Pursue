package com.liang.dubbo.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.liang.dubbo.model.User;
import com.liang.dubbo.service.UserRestService;
import com.liang.dubbo.service.UserService;

@Service("userRestService")
@Path("users")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class UserRestServiceImpl implements UserRestService {

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	@GET
	@Path("{id : \\d+}")
	public User getUser(@PathParam("id") Long id) {
		if (RpcContext.getContext().getRequest(HttpServletRequest.class) != null) {
			System.out.println("Client IP address from RpcContext: "
					+ RpcContext.getContext()
							.getRequest(HttpServletRequest.class)
							.getRemoteAddr());
		}
		if (RpcContext.getContext().getResponse(HttpServletResponse.class) != null) {
			System.out.println("Response object from RpcContext: "
					+ RpcContext.getContext().getResponse(
							HttpServletResponse.class));
		}
		return userService.getUser(id);
	}

}
