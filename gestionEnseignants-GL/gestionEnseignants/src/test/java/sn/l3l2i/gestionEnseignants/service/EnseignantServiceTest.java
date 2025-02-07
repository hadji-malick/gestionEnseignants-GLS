package sn.l3l2i.gestionEnseignants.service;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.l3l2i.gestionEnseignants.models.Enseignant;
import sn.l3l2i.gestionEnseignants.repository.EnseignantRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EnseignantServiceTest {
    @Mock
    private EnseignantRepository  enseignantRepository;
    @InjectMocks
    private EnseignantService enseignantService;



@Test
    public void testTrouverEnseignant(){
        Enseignant enseignant = new Enseignant(1L, "NdiayeBa", "Cheikhna", "NdiayeBacheikhna@gmail.com");
                when(enseignantRepository.findById(1L)).thenReturn(java.util.Optional.of(enseignant));
        Enseignant foundEnseignant = enseignantService.trouverEnseignantParId(1L);
        assertNotNull(foundEnseignant);
        assertEquals("NdiayeBa", foundEnseignant.getNom());
        verify(enseignantRepository, times(1)).findById(1L);
}



    @Test
        public void testAjouterEnseignant() {
            Enseignant enseignant = new Enseignant(1L, "NdiayeBa", "Cheikhna", "NdiayeBacheikhna@gmail.com");

            when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);

            Enseignant savedEnseignant = enseignantService.ajouterEnseignant(enseignant);

            assertNotNull(savedEnseignant);
            assertEquals("NdiayeBa", savedEnseignant.getNom());
            verify(enseignantRepository, times(1)).save(enseignant);
        }

    @Test
    public void testTrouverEnseignantParId_NotFound() {
        when(enseignantRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            enseignantService.trouverEnseignantParId(1L);
        });
    }

    @Test
    public void testAjouterEnseignant_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            enseignantService.ajouterEnseignant(null);
        });
    }


    }

