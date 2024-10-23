package <%= packageName %>.entity;

<%_ if (persistenceType === 'mybatis-plus') { _%>
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
<%_ } _%>
import java.util.Objects;

<%_ if (persistenceType === 'jpa') { _%>
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
<%_ } _%>
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<%_ if (persistenceType === 'jpa') { _%>
@Entity
@Table(name = "<%= tableName %>")
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
@TableName(value = "<%= tableName %>")
<%_ } _%>
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %> {

<%_ if (persistenceType === 'jpa') { _%>
    @Id
    <%_ if (!doesNotSupportDatabaseSequences) { _%>
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    <%_ } _%>
    <%_ if (doesNotSupportDatabaseSequences) { _%>
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    <%_ } _%>
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
    @TableId
<%_ } _%>
    private Long id;

<%_ if (persistenceType === 'jpa') { _%>
    @Column(nullable = false)
<%_ } _%>
<%_ if (persistenceType === 'mybatis-plus') { _%>
    @TableField
<%_ } _%>
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || this.getClass() != o.getClass()) { return false; }
        <%= entityName %> <%= entityVarName %> = (<%= entityName %>) o;
        return id != null && Objects.equals(id, <%= entityVarName %>.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
