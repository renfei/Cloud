package net.renfei.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import net.renfei.gateway.config.GatewayConfig;
import net.renfei.gateway.service.AuthorizationService;
import net.renfei.sdk.comm.StateCode;
import net.renfei.sdk.entity.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 授权过滤器，可以判断用户是否有权访问
 *
 * @author RenFei
 */
@Component
public class AuthorizationFilter extends ZuulFilter {
    @Autowired
    private GatewayConfig gatewayConfig;
    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 5;
    }

    @Override
    public boolean shouldFilter() {
        if (gatewayConfig.getDevMode()) {
            //开发模式不做校验
            return false;
        }
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = request.getRequestURI();
        if (url.startsWith("/api")) {
            AtomicBoolean checkIgnore = new AtomicBoolean(true);
            gatewayConfig.getIgnore().forEach(u -> {
                if (url.startsWith(u)) {
                    checkIgnore.set(false);
                }
            });
            return checkIgnore.get();
        } else {
            return false;
        }
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        APIResult apiResult = authorizationService.authorization(request);
        if (!StateCode.OK.getCode().equals(apiResult.getCode())) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody(JSON.toJSONString(apiResult));
            requestContext.setResponseStatusCode(apiResult.getCode());
            requestContext.getResponse().setContentType("application/json;charset=UTF-8");
        }
        return null;
    }
}
