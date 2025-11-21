package Repository;


import Entidades.SimulacaoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SPPDRepository implements PanacheRepository<SimulacaoEntity> {
    // Métodos customizados podem ser adicionados aqui
}
