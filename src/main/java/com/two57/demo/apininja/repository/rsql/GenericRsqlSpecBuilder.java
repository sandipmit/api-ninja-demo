package com.two57.demo.apininja.repository.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * filter = "company.code==demo"; //equal
 * filter = "company.code==''"; //equal to empty string
 * filter = "company.code==dem*"; //like dem%
 * filter = "company.code==*emo"; //like %emo
 * filter = "company.code==*em*"; //like %em%
 * filter = "company.code==^*EM*"; //ignore case like %EM%
 * filter = "company.code!=demo"; //not equal
 * filter = "company.code=in=(demo,real)"; //in
 * filter = "company.code=out=(demo,real)"; //not in
 * filter = "company.id=gt=100"; //greater than
 * filter = "company.id=lt=100"; //less than
 * filter = "company.id=ge=100"; //greater than or equal
 * filter = "company.id=le=100"; //less than or equal
 * filter = "company.id>100"; //greater than
 * filter = "company.id<100"; //less than
 * filter = "company.id>=100"; //greater than or equal
 * filter = "company.id<=100"; //less than or equal
 * filter = "company.code=isnull=''"; //is null
 * filter = "company.code=null=''"; //is null
 * filter = "company.code=na=''"; //is null
 * filter = "company.code=nn=''"; //is not null
 * filter = "company.code=notnull=''"; //is not null
 * filter = "company.code=isnotnull=''"; //is not null
 * @param <T>
 */
public class GenericRsqlSpecBuilder<T> {

    public Specification<T> createSpecification(final Node node) {
        if (node instanceof LogicalNode) {
            return createSpecification((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createSpecification((ComparisonNode) node);
        }
        return null;
    }

    public Specification<T> createSpecification(final LogicalNode logicalNode) {

        List<Specification<T>> specs = logicalNode.getChildren()
                .stream()
                .map(node -> createSpecification(node))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Specification<T> result = specs.get(0);
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).and(specs.get(i));
            }
        }
        else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < specs.size(); i++) {
                result = Specification.where(result).or(specs.get(i));
            }
        }

        return result;
    }

    public Specification<T> createSpecification(final ComparisonNode comparisonNode) {
        return Specification.where(new GenericRsqlSpecification<T>(comparisonNode.getSelector(), comparisonNode.getOperator(), comparisonNode.getArguments()));
    }

}
