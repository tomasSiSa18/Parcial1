package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class TallerEntity extends BaseEntity {
    
    private String nombre;
    private String slogan;

    @Temporal(TemporalType.DATE)
    private Calendar fechaFundacion;

    @PodamExclude
    @ManyToMany
    private List<MarcaEntity> marcasTaller = new ArrayList<>();

}
