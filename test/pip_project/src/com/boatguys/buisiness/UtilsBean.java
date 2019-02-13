package com.boatguys.buisiness;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Path("utils")
@Stateless
@Local(Utils.class)
public class UtilsBean implements Utils
{
    @POST
    @Path("/imgUpload/{name}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public Response uploadImage(
            @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @PathParam("name") String name)
    {
        String uploadedFilePath ="../applications/webApp/"+name;

        File  objFile = new File(uploadedFilePath);

        if(objFile.exists())
        {
            objFile.delete();

        }

        saveToFile(uploadedInputStream, uploadedFilePath);

        Map<String, String> urlObject = new HashMap<>();
        urlObject.put("url", objFile.getAbsolutePath());

        return Response.status(200).entity(urlObject).build();
    }

    @Path("/imgGet/{name}")
    @Produces("image/*")
    @RolesAllowed({"users", "administrators", "moderators"})
    @Override
    public Response getImage(@PathParam("name") String url)
    {
        File imgFile = new File("../img/"+url);

        if (!imgFile.exists())
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(imgFile);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        if(fileInputStream != null)
        {
            Response.ResponseBuilder builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            return builder.status(Response.Status.OK).type("image/png").entity(fileInputStream).build();

            //return Response.ok(fileInputStream).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    private void saveToFile(InputStream uploadedInputStream,
                            String uploadedFilePath)
    {

        try
        {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFilePath));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
