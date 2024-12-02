package com.microboxlabs.dist.rest;

import com.microboxlabs.service.LogService;
import com.microboxlabs.service.contract.form.FileUploadForm;
import com.microboxlabs.service.contract.to.criteria.AdvanceCriteriaTO;
import com.microboxlabs.service.contract.to.criteria.CriteriaTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/logs")
public class LogResource {

    private final LogService logService;
    private static final Logger logger = LoggerFactory.getLogger(LogResource.class);

    public LogResource(LogService logService) {
        this.logService = logService;
    }

    @POST
    @Path("/upload")
    @RolesAllowed("admin")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@MultipartForm FileUploadForm form) {
        try {
            logger.info("Perform api/logs/upload");
            logService.parseAndSaveLogs(form.getFile());
            return Response
                    .ok()
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("")
    @RolesAllowed({"admin", "user"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        try {
            logger.info("Perform api/logs/");
            final var result = logService.findAll(new CriteriaTO().withPage(page).withSize(size));
            return Response
                    .ok(result)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/filter")
    @RolesAllowed({"admin", "user"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(AdvanceCriteriaTO criteria) {
        try {
            logger.info("Perform api/logs/filter");
            final var result = logService.findAll(criteria);
            return Response
                    .ok(result)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
