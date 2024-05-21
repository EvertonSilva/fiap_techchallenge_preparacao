package br.com.lanchonete;

import br.com.lanchonete.service.StatusPedidoService;
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
    StatusPedidoService service;

    @Inject
    public PreparacaoResource(AtualizaStatusPedidoUseCase useCase, StatusPedidoService service) {
        this.useCase = useCase;
        this.service = service;
    }

    @GET
    @Path("/health")
    public Response healthCheck() {
        return Response.status(Response.Status.OK).entity("Service is running").build();
    }

    @GET
    @Path("/{idPedido}/status")
    public Response getStatusPedido(@PathParam("idPedido") String idPedido) {
        var pedido = service.getStatusPedido(idPedido);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.status(Response.Status.OK)
                .entity(pedido)
                .build();
    }

    @PATCH
    @Path("/{idPedido}")
    public Response atualizaStatusPedido(@PathParam("idPedido") String idPedido, @QueryParam("status") String status) {
        useCase.execute(idPedido, status);
        return Response.status(Response.Status.OK).build();
    }
}
