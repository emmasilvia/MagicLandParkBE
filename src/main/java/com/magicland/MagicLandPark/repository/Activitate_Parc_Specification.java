package com.magicland.MagicLandPark.repository;

import com.magicland.MagicLandPark.model.Activitate_Parc;
//import com.magicland.MagicLandPark.model.NivelDificultate;
import com.magicland.MagicLandPark.model.NivelDificultate;
import com.magicland.MagicLandPark.model.TipActivitate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class Activitate_Parc_Specification implements Specification<Activitate_Parc> {

    public static Specification<Activitate_Parc> withNameLike(String activitateDenumire){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("denumire"), "%" + activitateDenumire + "%");
    }

    public static Specification<Activitate_Parc> ofType(TipActivitate tipActivitate){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("tipActivitate"), tipActivitate);
    }

    public static Specification<Activitate_Parc> ofLevel(NivelDificultate nivelDificultate){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("nivelDificultate"), nivelDificultate);
    }

    public static Specification<Activitate_Parc> withProgram(String program){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("program"), "%" + program + "%");
    }

    public static Specification<Activitate_Parc> withDurataInRange(Integer lowerInterval, Integer higherInterval) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("durata"), lowerInterval,higherInterval);
    }

    public static Specification<Activitate_Parc> withVarstaMinima(Integer lowerInterval, Integer higherInterval) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("varstaMinima"), lowerInterval, higherInterval);
    }

    public static Specification<Activitate_Parc> withActivitatiId(Long activitateId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Activitate_Parc, Activitate_Parc> categoryJoin = root.join("activitate");
            return criteriaBuilder.equal(categoryJoin.get("id"), activitateId);
        };
    }

    public static Specification<Activitate_Parc> withActivitateIdsIn(List<Long> activitateIds) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Activitate_Parc, Activitate_Parc> categoryJoin = root.join("activitate");
            return categoryJoin.get("id").in(activitateIds);
        };
    }


    public static Specification<Activitate_Parc> getSpecificationByParameter(String parameterName, String parameterValue) {
        switch (parameterName) {
            case "denumire":
                return withNameLike(parameterValue);
            case "tipActivitate":
                return ofType(TipActivitate.valueOf(parameterValue));
            case "nivelDificultate":
                return ofLevel(NivelDificultate.valueOf(parameterValue));
//            case "varstaMinima":
//                String varsta = parameterValue;
//                Integer varstaMinima = 0;
//                Integer varstaMaxima = Integer.MAX_VALUE;
//                if (varsta.contains(",")) {
//                    List<String> intervalVarsta = Arrays.asList(varsta.split(","));
//                    for (String iteration : intervalVarsta) {
//                        if (iteration.contains("varstaMinima")) {
//                            String varstaMinInStringFormat = iteration.replaceAll("varstaMinima:", "");
//                            varstaMinima = Integer.valueOf(varstaMinInStringFormat);
//                        } else if (iteration.contains("varstaMaxima")) {
//                            {
//                                String varstaMaxInStringFormat = iteration.replaceAll("varstaMaxima:", "");
//                                varstaMaxima = Integer.valueOf(varstaMaxInStringFormat);
//                            }
//                        }
//                    }
//                }
//                return withVarstaMinima(varstaMinima, varstaMaxima);
            case "durata":
                String durata = parameterValue;
                Integer durataMinima = 0;
                Integer durataMaxima = Integer.MAX_VALUE;
                if (durata.contains(",")) {
                    List<String> intervalDurata = Arrays.asList(durata.split(","));
                    for (String iteration : intervalDurata) {
                        if (iteration.contains("durataMinima")) {
                            String durataMinInStringFormat = iteration.replaceAll("durataMinima:", "");
                            durataMinima = Integer.valueOf(durataMinInStringFormat);
                        } else if (iteration.contains("durataMaxima")) {
                            {
                                String durataMaxInStringFormat = iteration.replaceAll("durataMaxima:", "");
                                durataMaxima = Integer.valueOf(durataMaxInStringFormat);
                            }
                        }
                    }
                }
                return withDurataInRange(durataMinima, durataMaxima);

            default:
                return new Activitate_Parc_Specification();
        }
    }

    public static Specification<Activitate_Parc> getSpecificationByParameterWithMultipleValues(String parameter, List<Long> ids){
        switch (parameter) {
            case "id": return withActivitateIdsIn(ids);
            default: return new Activitate_Parc_Specification();
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
