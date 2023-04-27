package tn.crashcode.campsidelocal.Services;

import tn.crashcode.campsidelocal.Entities.ForumSection;

import java.util.List;

public interface I_ForumSectionService {
    ForumSection CreateForumSection(ForumSection forumSection);
    void DeleteForumSection(int  idForumSection);
    ForumSection UpdateForumSection(ForumSection forumSection);
    ForumSection RetriveForumSection(int  idSection);

    List<ForumSection> RetriveAllForumSections();
}
