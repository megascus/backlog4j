package com.nulabinc.backlog4j.internal.json;

import com.nulabinc.backlog4j.*;
import com.nulabinc.backlog4j.internal.json.activities.*;
import org.junit.Test;
import uk.co.it.modular.hamcrest.date.DateMatchers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by yuh kim on 2014/07/23.
 */
public class ActivityJSONImplTest extends AbstractJSONImplTest {


    //
    @Test
    public void createActivityIssueCreatedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_issue_created.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.IssueCreated ,activity.getType());

        IssueCreatedActivity issueCreated = (IssueCreatedActivity) activity;

        assertEquals(624929, issueCreated.getId());

        Project project = issueCreated.getProject();
        assertEquals(1073761031, project.getId());
        assertEquals("TEST_PRJ1407208757143", project.getProjectKey());
        assertEquals("テストプロジェクト", project.getName());
        assertEquals(false, project.isUseGantt());
        assertEquals(false, project.isUseBurndown());
        assertEquals(false, project.isChartEnabled());
        assertEquals(true, project.isSubtaskingEnabled());
        assertEquals("backlog", project.getTextFormattingRule());
        assertEquals(false, project.isArchived());

        IssueCreatedContent content = issueCreated.getContent();
        assertEquals(1073803919, content.getId());
        assertEquals(1, content.getKeyId());
        assertEquals("課題作成", content.getSummary());
        assertEquals("テスト", content.getDescription());

        User user = issueCreated.getCreatedUser();
        assertEquals(1073751781, user.getId());
        assertEquals("test_admin", user.getUserId());
        assertEquals("あどみにさん", user.getName());
        assertEquals(User.RoleType.Admin, user.getRoleType());
        assertEquals(null, user.getLang());
        assertEquals("test@test.test", user.getMailAddress());

        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.AUGUST, 5, 5, 50, 59);
        assertThat(calendar.getTime(), DateMatchers.sameDay(issueCreated.getCreated()));
    }

    @Test
    public void createActivityIssueUpdatedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_issue_updated.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.IssueUpdated ,activity.getType());

        IssueUpdatedActivity issueUpdated = (IssueUpdatedActivity) activity;

        assertEquals(39293172, issueUpdated.getId());


        IssueUpdatedContent content = issueUpdated.getContent();
        assertEquals(1079097008, content.getId());
        assertEquals(1, content.getKeyId());
        assertEquals("課題追加テスト", content.getSummary());
        assertEquals("", content.getDescription());

        Comment comment = content.getComment();
        assertEquals(1102443987, comment.getId());
        assertEquals( "コメント時ファイル添付テスト", comment.getContent());

        List<Attachment> attachments = content.getAttachments();
        assert attachments.size() > 0;
        Attachment attachment = attachments.get(0);
        assertEquals(1076499483, attachment.getId());
        assertEquals(attachment.getName(), "twoshot.JPG");
        assertEquals(86513, attachment.getSize());

        List<Change> changes = content.getChanges();
        assert changes.size() > 0;
        Change change = changes.get(0);
        assertEquals("attachment", change.getField());
        assertEquals("twoshot.JPG", change.getNewValue());
        assertEquals("", change.getOldValue());
        assertEquals("standard", change.getType());

    }

    @Test
    public void createActivityIssueCommentedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_issue_commented.json");
        Activity activity = factory.createActivity(fileContentStr);


        assertEquals(Activity.Type.IssueCommented, activity.getType());

        IssueCommentedActivity issueCommented = (IssueCommentedActivity) activity;

        assertEquals(39261460, issueCommented.getId());

        IssueCommentedContent content = issueCommented.getContent();
        assertEquals(1079097008, content.getId());
        assertEquals(1, content.getKeyId());
        assertEquals("課題追加テスト", content.getSummary());
        assertEquals("", content.getDescription());
        Comment comment = content.getComment();
        assertEquals(1102419188, comment.getId());
        assertEquals("コメント時ファイル添付テスト", comment.getContent());

    }

    @Test
    public void createActivityWikiCreatedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_wiki_created.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.WikiCreated, activity.getType());
        WikiCreatedActivity wikiCreated = (WikiCreatedActivity) activity;

        assertEquals(624763, wikiCreated.getId());
        WikiCreatedContent content = wikiCreated.getContent();
        assertEquals(1073768599, content.getId());
        assertEquals("テスト", content.getName());
        assertEquals("ああああ", content.getContent());


    }

    @Test
    public void createActivityWikiUpdatedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_wiki_updated.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.WikiUpdated, activity.getType());
        WikiUpdatedActivity wikiUpdated = (WikiUpdatedActivity) activity;

        assertEquals(624764, wikiUpdated.getId());
        WikiUpdatedContent content = wikiUpdated.getContent();
        assertEquals(1073768599, content.getId());
        assertEquals("テスト", content.getName());
        assertEquals("ああああ", content.getContent());
        List<Attachment> attachments = content.getAttachments();
        assertEquals(18105, attachments.get(0).getId());
        assertEquals("backlog_icon.png", attachments.get(0).getName());
        assertEquals(5050, attachments.get(0).getSize());
    }

    @Test
    public void createActivityProjectUserAddedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_project_user_added.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.ProjectUserAdded, activity.getType());
        ProjectUserAddedActivity projectUserAdded = (ProjectUserAddedActivity) activity;

        assertEquals(623680, projectUserAdded.getId());

        ProjectUserAddedContent content = projectUserAdded.getContent();
        List<User> users = content.getUsers();
        assertEquals(1073751782, users.get(0).getId());

        List<GroupProjectActivity> groupProjectActivities = content.getGroupProjectActivities();
        assertEquals(623680, groupProjectActivities.get(0).getId());
        assertEquals(Activity.Type.ProjectUserAdded, groupProjectActivities.get(0).getType());


    }

    @Test
    public void createActivityProjectUserRemovedTest() throws IOException {
        String fileContentStr = getJsonString("json/activity_project_user_removed.json");
        Activity activity = factory.createActivity(fileContentStr);

        assertEquals(Activity.Type.ProjectUserRemoved, activity.getType());
        ProjectUserRemovedActivity projectUserRemoved = (ProjectUserRemovedActivity) activity;

        assertEquals(623559, projectUserRemoved.getId());


        ProjectUserRemovedContent content = projectUserRemoved.getContent();
        List<User> users = content.getUsers();
        assertEquals(1073936985, users.get(0).getId());
        assertEquals("test_admin", users.get(0).getUserId());
        assertEquals(1073936985, users.get(0).getId());

        List<GroupProjectActivity> groupProjectActivities = content.getGroupProjectActivities();
        assertEquals(623554, groupProjectActivities.get(0).getId());
        assertEquals(Activity.Type.ProjectUserRemoved, groupProjectActivities.get(0).getType());

        assertEquals("よろしく", content.getComment());


    }
}
