package ru.sber.csportal.entity.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import ru.sber.csportal.entity.base.AbstractEntity;
import ru.sber.csportal.entity.catalog.ContractEntity;
import ru.sber.csportal.entity.catalog.PartnerEntity;
import ru.sber.csportal.entity.request.catalog.RequestStatusEntity;
import ru.sber.csportal.entity.request.catalog.RequestTypeEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "request")
public class RequestEntity extends AbstractEntity {
    @Column(name = "request_number", unique = true)
    @NumberFormat(style = Style.NUMBER)
    private Long requestNumber;

    @Column(name = "request_description")
    private String requestDescription;

    @Column(name = "request_template")
    private String requestTemplate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_type")
    private RequestTypeEntity requestType;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private RequestStatusEntity requestStatus;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private ContractEntity contract;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "request", orphanRemoval = true)
    private Set<RequestItemEntity> requestItems;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "request_partner",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "initiator_id"))
    private Set<PartnerEntity> initiator;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "request_partner",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "caller_id"))
    private Set<PartnerEntity> caller;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "request_partner",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id"))
    private Set<PartnerEntity> worker;


    public void setRequestItems(Set<RequestItemEntity> requestItems) {
        if (requestItems != null) {
            requestItems.forEach(requestItem -> requestItem.setRequest(this));
            this.requestItems = requestItems;
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RequestEntity that = (RequestEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "requestNumber = " + getRequestNumber() + ", " +
                "requestDescription = " + getRequestDescription() + ", " +
                "requestTemplate = " + getRequestTemplate() + ")";
    }
}