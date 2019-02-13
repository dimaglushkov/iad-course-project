package com.boatguys.buisiness;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Local
public interface Utils
{

    @RolesAllowed({"users", "administrators", "moderators"})
    Response uploadImage(
            boolean enabled,
            InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            String name);


    @RolesAllowed({"users", "administrators", "moderators"})
    Response getImage(String url);
}
