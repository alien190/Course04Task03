package com.example.alien.course04task03.model;

public class User{
	private String gistsUrl;
	private String reposUrl;
	private String followingUrl;
	private String bio;
	private String createdAt;
	private String login;
	private String type;
	private String blog;
	private String subscriptionsUrl;
	private String updatedAt;
	private boolean siteAdmin;
	private String company;
	private int id;
	private int publicRepos;
	private String gravatarId;
	private String email;
	private String organizationsUrl;
	private boolean hireable;
	private String starredUrl;
	private String followersUrl;
	private int publicGists;
	private String url;
	private String receivedEventsUrl;
	private int followers;
	private String avatarUrl;
	private String eventsUrl;
	private String htmlUrl;
	private int following;
	private String name;
	private String location;
	private String nodeId;

	public void setGistsUrl(String gistsUrl){
		this.gistsUrl = gistsUrl;
	}

	public String getGistsUrl(){
		return gistsUrl;
	}

	public void setReposUrl(String reposUrl){
		this.reposUrl = reposUrl;
	}

	public String getReposUrl(){
		return reposUrl;
	}

	public void setFollowingUrl(String followingUrl){
		this.followingUrl = followingUrl;
	}

	public String getFollowingUrl(){
		return followingUrl;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBio(){
		return bio;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setLogin(String login){
		this.login = login;
	}

	public String getLogin(){
		return login;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setBlog(String blog){
		this.blog = blog;
	}

	public String getBlog(){
		return blog;
	}

	public void setSubscriptionsUrl(String subscriptionsUrl){
		this.subscriptionsUrl = subscriptionsUrl;
	}

	public String getSubscriptionsUrl(){
		return subscriptionsUrl;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setSiteAdmin(boolean siteAdmin){
		this.siteAdmin = siteAdmin;
	}

	public boolean isSiteAdmin(){
		return siteAdmin;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPublicRepos(int publicRepos){
		this.publicRepos = publicRepos;
	}

	public int getPublicRepos(){
		return publicRepos;
	}

	public void setGravatarId(String gravatarId){
		this.gravatarId = gravatarId;
	}

	public String getGravatarId(){
		return gravatarId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setOrganizationsUrl(String organizationsUrl){
		this.organizationsUrl = organizationsUrl;
	}

	public String getOrganizationsUrl(){
		return organizationsUrl;
	}

	public void setHireable(boolean hireable){
		this.hireable = hireable;
	}

	public boolean isHireable(){
		return hireable;
	}

	public void setStarredUrl(String starredUrl){
		this.starredUrl = starredUrl;
	}

	public String getStarredUrl(){
		return starredUrl;
	}

	public void setFollowersUrl(String followersUrl){
		this.followersUrl = followersUrl;
	}

	public String getFollowersUrl(){
		return followersUrl;
	}

	public void setPublicGists(int publicGists){
		this.publicGists = publicGists;
	}

	public int getPublicGists(){
		return publicGists;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setReceivedEventsUrl(String receivedEventsUrl){
		this.receivedEventsUrl = receivedEventsUrl;
	}

	public String getReceivedEventsUrl(){
		return receivedEventsUrl;
	}

	public void setFollowers(int followers){
		this.followers = followers;
	}

	public int getFollowers(){
		return followers;
	}

	public void setAvatarUrl(String avatarUrl){
		this.avatarUrl = avatarUrl;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public void setEventsUrl(String eventsUrl){
		this.eventsUrl = eventsUrl;
	}

	public String getEventsUrl(){
		return eventsUrl;
	}

	public void setHtmlUrl(String htmlUrl){
		this.htmlUrl = htmlUrl;
	}

	public String getHtmlUrl(){
		return htmlUrl;
	}

	public void setFollowing(int following){
		this.following = following;
	}

	public int getFollowing(){
		return following;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setNodeId(String nodeId){
		this.nodeId = nodeId;
	}

	public String getNodeId(){
		return nodeId;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"gists_url = '" + gistsUrl + '\'' + 
			",repos_url = '" + reposUrl + '\'' + 
			",following_url = '" + followingUrl + '\'' + 
			",bio = '" + bio + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",login = '" + login + '\'' + 
			",type = '" + type + '\'' + 
			",blog = '" + blog + '\'' + 
			",subscriptions_url = '" + subscriptionsUrl + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",site_admin = '" + siteAdmin + '\'' + 
			",company = '" + company + '\'' + 
			",id = '" + id + '\'' + 
			",public_repos = '" + publicRepos + '\'' + 
			",gravatar_id = '" + gravatarId + '\'' + 
			",email = '" + email + '\'' + 
			",organizations_url = '" + organizationsUrl + '\'' + 
			",hireable = '" + hireable + '\'' + 
			",starred_url = '" + starredUrl + '\'' + 
			",followers_url = '" + followersUrl + '\'' + 
			",public_gists = '" + publicGists + '\'' + 
			",url = '" + url + '\'' + 
			",received_events_url = '" + receivedEventsUrl + '\'' + 
			",followers = '" + followers + '\'' + 
			",avatar_url = '" + avatarUrl + '\'' + 
			",events_url = '" + eventsUrl + '\'' + 
			",html_url = '" + htmlUrl + '\'' + 
			",following = '" + following + '\'' + 
			",name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",node_id = '" + nodeId + '\'' + 
			"}";
		}
}
