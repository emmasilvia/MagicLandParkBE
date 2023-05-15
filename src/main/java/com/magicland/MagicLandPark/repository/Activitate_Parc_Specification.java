package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Activitate_Parc;
import com.magicland.MagicLandPark.model.TipActivitate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Activitate_Parc_Specification implements Specification<Activitate_Parc> {

    public static Specification<Activitate_Parc> withNameLike(String activitateDenumire){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("denumire"), "%" + activitateDenumire + "%");
    }

    public static Specification<Activitate_Parc> ofType(TipActivitate tipActivitate){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipActivitate"), tipActivitate);
    }

    public static Specification<Activitate_Parc> getSpecificationByParameter(String parameterName, String parameterValue) {
        switch (parameterName) {
            case "denumire":
                return withNameLike(parameterValue);
            case "tipActivitate":
                return ofType(TipActivitate.valueOf(parameterValue));
            default:
                return new Activitate_Parc_Specification();
        }
    }

    @Override
    public Specification<Activitate_Parc> and(Specification<Activitate_Parc> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Activitate_Parc> or(Specification<Activitate_Parc> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<Activitate_Parc> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
