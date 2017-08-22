package net.avidreder.lead_measure.domain;

import spark.*;

import static net.avidreder.lead_measure.Application.domainDao;
import static spark.Spark.halt;

import net.avidreder.lead_measure.util.*;
//import static app.util.RequestUtil.*;

public class DomainController {

    public static Route getAllDomains = (Request request, Response response) -> {
        return JsonUtil.dataToJson(domainDao.getAllDomains());
    };
    public static Route createNewDomain = (Request request, Response response) -> {
        String domainName = request.queryParams("domainName");
        if (domainName == null) {
            response.type("application/json");
            halt(400,"{\"error\":\"Bad Request\", \"message\":\"Missing required parameter domainName\"}");
        }
        return JsonUtil.dataToJson(domainDao.createDomain(domainName));
    };
}
