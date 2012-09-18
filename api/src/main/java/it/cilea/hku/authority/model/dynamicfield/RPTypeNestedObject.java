package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.osd.jdyna.model.ATypeNestedObject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
*
* @author pascarelli
*
*/
@Entity
@Table(name = "model_rp_jdyna_nestedobject_typo")
@NamedQueries ({
    @NamedQuery(name="RPTypeNestedObject.findAll", query = "from RPTypeNestedObject order by id" ),
    @NamedQuery(name="RPTypeNestedObject.uniqueByNome", query = "from RPTypeNestedObject where nome = ?" )
})
public class RPTypeNestedObject extends ATypeNestedObject<RPNestedPropertiesDefinition>
{
    @ManyToMany
    @JoinTable(name = "model_rp_jdyna_nestedobject_typo2mask")
    @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<RPNestedPropertiesDefinition> mask;

    @Override
    public List<RPNestedPropertiesDefinition> getMaschera()
    {
        return mask;
    }

    public void setMaschera(List<RPNestedPropertiesDefinition> mask) {
        this.mask = mask;
    }

}
