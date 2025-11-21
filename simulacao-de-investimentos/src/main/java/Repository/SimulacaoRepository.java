package Repository;

import Entidades.Simulacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class SimulacaoRepository implements PanacheRepository<Simulacao> {
    // Métodos customizados podem ser adicionados aqui
}
