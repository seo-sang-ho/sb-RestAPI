package com.ll.sbrestapi.domain.member.article.article.controller;

import com.ll.sbrestapi.domain.member.article.article.dto.ArticleDto;
import com.ll.sbrestapi.domain.member.article.article.entity.Article;
import com.ll.sbrestapi.domain.member.article.article.service.ArticleService;
import com.ll.sbrestapi.domain.member.member.entity.Member;
import com.ll.sbrestapi.global.rq.Rq;
import com.ll.sbrestapi.global.rsData.RsData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticlesController {
    private final ArticleService articleService;
    private final Rq rq;

    @Getter
    public static class GetArticlesResponseBody{
        private final List<ArticleDto> items;
        private final Map pagingNation;

        public GetArticlesResponseBody(List<Article> articles) {
            this.items = articles
                    .stream()
                    .map(ArticleDto::new)
                    .toList();

            pagingNation = Map.of("page",1);
        }
    }

    @GetMapping("")
    public RsData<GetArticlesResponseBody> getArticles(){
        return RsData.of("200","성공",new GetArticlesResponseBody(articleService.findAllByOrderByIdDesc()));
    }

    @Getter
    public static class GetArticleResponseBody{
        private final ArticleDto item;

        public GetArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @GetMapping("/{id}")
    public RsData<GetArticleResponseBody> getArticle(@PathVariable long id){
        return RsData.of("200","성공",new GetArticleResponseBody(articleService.findById(id).get()));
    }

    @Getter
    public static class RemoveArticleResponseBody{
        private final ArticleDto item;

        public RemoveArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @DeleteMapping("/{id}")
    public RsData<RemoveArticleResponseBody> removeArticle(@PathVariable long id){
        Article article = articleService.findById(id).get();

        articleService.deleteById(id);

        return RsData.of("200","성공",new RemoveArticleResponseBody(article));
    }

    @Getter
    @Setter
    public static class ModifyArticleRequestBody{
        private String title;
        private String body;
    }

    @Getter
    public static class ModifyArticleResponseBody{
        private final ArticleDto item;

        public ModifyArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PutMapping("/{id}")
    public RsData<ModifyArticleResponseBody> modifyArticle(@PathVariable long id,@RequestBody ModifyArticleRequestBody body){
        Article article = articleService.findById(id).get();

        articleService.modify(article,body.getTitle(),body.getBody());

        return RsData.of("200","성공",new ModifyArticleResponseBody(article));
    }

    @Getter
    @Setter
    public static class WriteArticleRequestBody{
        private String title;
        private String body;
    }

    @Getter
    public static class WriteArticleResponseBody{
        private final ArticleDto item;

        public WriteArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("")
    public RsData<?> writeArticle(@RequestBody WriteArticleRequestBody body){
        Member member = rq.getMember();

        RsData<Article> writeRs = articleService.write(member, body.getTitle(), body.getBody());

        return writeRs.of(
                new WriteArticleResponseBody(
                        writeRs.getData()
                )
        );
    }
}
