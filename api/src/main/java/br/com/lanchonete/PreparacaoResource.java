package br.com.lanchonete;

import java.util.UUID;

import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/preparacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PreparacaoResource {
    AtualizaStatusPedidoUseCase useCase;

    @Inject
    public PreparacaoResource(AtualizaStatusPedidoUseCase useCase) {
        this.useCase = useCase;
    }

    @GET
    @Path("/health")
    public Response healthCheck() {
        var clazz = useCase.getClass();
        return Response.status(Response.Status.OK).entity("Service is running").build();
    }

    @GET
    public Response getStatusTodosPedidos() {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{idPedido}/status")
    public Response getStatusPedido(@PathParam("idPedido") UUID idPedido) {
        return Response.status(Response.Status.OK)
                .entity("Status do pedido " + idPedido)
                .build();
    }

    @PATCH
    @Path("/{idPedido}")
    public Response atualizaStatusPedido(@PathParam("idPedido") UUID idPedido, @QueryParam("status") String status) {
        useCase.execute(idPedido, status);
        return Response.status(Response.Status.OK).build();
    }
}
