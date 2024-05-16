package br.com.lanchonete;

import br.com.lanchonete.usecase.AtualizaStatusPedidoUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.UUID;

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
