package com.example.alien.course04task03.repository.gitHubRepository;

import com.example.alien.course04task03.model.NewRepoRequest;
import com.example.alien.course04task03.model.NewRepoResponse;
import com.example.alien.course04task03.model.User;
import com.example.alien.course04task03.ui.common.SingleFragmentActivity;

import io.reactivex.Single;

public interface IGHRepository {
    Single<Boolean> validateToken(String token);
    Single<String> createToken(String code, String clientId, String clientSecret);
    Single<User> getUser(String token);
    Single<NewRepoResponse> createRepo(NewRepoRequest newRepoRequest);
}
