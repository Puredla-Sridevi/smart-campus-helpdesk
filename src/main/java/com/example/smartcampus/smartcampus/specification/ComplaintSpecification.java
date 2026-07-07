package com.example.smartcampus.smartcampus.specification;

import com.example.smartcampus.smartcampus.entity.Complaint;
import com.example.smartcampus.smartcampus.entity.ComplaintPriority;
import com.example.smartcampus.smartcampus.entity.ComplaintStatus;
import org.springframework.data.jpa.domain.Specification;

public class ComplaintSpecification {
    public static Specification<Complaint> haskeyword(String keyword) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
    }
    public static Specification<Complaint> hasStatus(ComplaintStatus status){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.equal(root.get("complaintStatus"),status);
    }
    public static Specification<Complaint> hasPriority(ComplaintPriority priority){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("complaintPriority"),priority));
    }
}
