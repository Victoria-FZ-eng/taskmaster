package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the TaskNew type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "TaskNews")
@Index(name = "byTaskNew", fields = {"teamId","title","body","state"})
public final class TaskNew implements Model {
  public static final QueryField ID = field("TaskNew", "id");
  public static final QueryField TEAM_ID = field("TaskNew", "teamId");
  public static final QueryField TITLE = field("TaskNew", "title");
  public static final QueryField BODY = field("TaskNew", "body");
  public static final QueryField STATE = field("TaskNew", "state");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="ID", isRequired = true) String teamId;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String body;
  private final @ModelField(targetType="String", isRequired = true) String state;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTeamId() {
      return teamId;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getBody() {
      return body;
  }
  
  public String getState() {
      return state;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private TaskNew(String id, String teamId, String title, String body, String state) {
    this.id = id;
    this.teamId = teamId;
    this.title = title;
    this.body = body;
    this.state = state;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      TaskNew taskNew = (TaskNew) obj;
      return ObjectsCompat.equals(getId(), taskNew.getId()) &&
              ObjectsCompat.equals(getTeamId(), taskNew.getTeamId()) &&
              ObjectsCompat.equals(getTitle(), taskNew.getTitle()) &&
              ObjectsCompat.equals(getBody(), taskNew.getBody()) &&
              ObjectsCompat.equals(getState(), taskNew.getState()) &&
              ObjectsCompat.equals(getCreatedAt(), taskNew.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), taskNew.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTeamId())
      .append(getTitle())
      .append(getBody())
      .append(getState())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("TaskNew {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("teamId=" + String.valueOf(getTeamId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("body=" + String.valueOf(getBody()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TeamIdStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static TaskNew justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new TaskNew(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      teamId,
      title,
      body,
      state);
  }
  public interface TeamIdStep {
    TitleStep teamId(String teamId);
  }
  

  public interface TitleStep {
    BodyStep title(String title);
  }
  

  public interface BodyStep {
    StateStep body(String body);
  }
  

  public interface StateStep {
    BuildStep state(String state);
  }
  

  public interface BuildStep {
    TaskNew build();
    BuildStep id(String id) throws IllegalArgumentException;
  }
  

  public static class Builder implements TeamIdStep, TitleStep, BodyStep, StateStep, BuildStep {
    private String id;
    private String teamId;
    private String title;
    private String body;
    private String state;
    @Override
     public TaskNew build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new TaskNew(
          id,
          teamId,
          title,
          body,
          state);
    }
    
    @Override
     public TitleStep teamId(String teamId) {
        Objects.requireNonNull(teamId);
        this.teamId = teamId;
        return this;
    }
    
    @Override
     public BodyStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public StateStep body(String body) {
        Objects.requireNonNull(body);
        this.body = body;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        Objects.requireNonNull(state);
        this.state = state;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String teamId, String title, String body, String state) {
      super.id(id);
      super.teamId(teamId)
        .title(title)
        .body(body)
        .state(state);
    }
    
    @Override
     public CopyOfBuilder teamId(String teamId) {
      return (CopyOfBuilder) super.teamId(teamId);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder body(String body) {
      return (CopyOfBuilder) super.body(body);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
  }
  
}
