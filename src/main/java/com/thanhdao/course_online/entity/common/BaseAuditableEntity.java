package com.thanhdao.course_online.entity.common;

import com.thanhdao.course_online.utils.ultilities.TimeUtils;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;

import static com.thanhdao.course_online.utils.constants.RequestConstants.currentUserAttributeName;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Slf4j
public class BaseAuditableEntity<U extends UserDetails, PK> extends BaseEntity<PK> {

    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    protected U createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    protected Timestamp createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @Nullable
    protected U lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    protected Timestamp lastModifiedDate;
    private U getCurrentUserFromRequestContext(){
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if(requestAttributes == null) return null;
        return (U) requestAttributes.getRequest().getAttribute(currentUserAttributeName);
    }
    @PrePersist
    public void onPrePersist() {
        U user = getCurrentUserFromRequestContext();
        if(user != null) {
            createdBy = user;
            lastModifiedBy = user;
        }
        createdDate = TimeUtils.getCurrentVietNamTime();
        lastModifiedDate = TimeUtils.getCurrentVietNamTime();

    }

    @PreUpdate
    public void onPreUpdate() {
        U user = getCurrentUserFromRequestContext();
        if(user != null) {
            lastModifiedBy = user;
        }
        lastModifiedDate = TimeUtils.getCurrentVietNamTime();
    }
}
