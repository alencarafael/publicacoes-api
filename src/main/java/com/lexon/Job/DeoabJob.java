package com.lexon.Job;

import com.lexon.Enum.UFOrganizacao;
import com.lexon.Service.DeoabSyncService;
import com.lexon.model.CadastroPublicacao;
import com.lexon.model.Tribunal;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class DeoabJob {

    private static final Logger LOGGER = Logger.getLogger(DeoabJob.class);

    @Inject
    DeoabSyncService deoabSyncService;

    @Transactional
    @Scheduled(every = "3m")
    public void executarConsultaPeriodica() {
        LOGGER.info("Iniciando job de sincronização com DEOAB...");

        List<CadastroPublicacao> publicacoes = CadastroPublicacao.listAll();
        LocalDate hoje = LocalDate.now();
        String dataFormatada = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        for (CadastroPublicacao cadastro : publicacoes) {
            try {
                String termo = cadastro.nomeAdvogado;
                int idOrganizacao = UFOrganizacao.getCodigoPorUf(cadastro.ufOab);


                deoabSyncService.sincronizarPublicacoes(
                        termo,
                        idOrganizacao,
                        "31/03/2025",
                        "31/03/2025",
//                        dataFormatada,
//                        dataFormatada,
                        cadastro
                );

                LOGGER.infof("Sincronização concluída para: %s (%s)", termo, cadastro.id);

            } catch (Exception e) {
                LOGGER.error("Erro ao processar sincronização de cadastro: " + cadastro.id, e);
            }
        }
    }
}