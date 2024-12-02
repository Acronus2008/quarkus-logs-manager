package com.microboxlabs.dist.rest;

import com.microboxlabs.service.LogService;
import com.microboxlabs.service.contract.form.FileUploadForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public Response uploadLogFile(@MultipartForm FileUploadForm form) {
        try {
            logger.info("Perform api/logs/upload");
            logService.parseAndSaveLogs(form.getFile());
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
