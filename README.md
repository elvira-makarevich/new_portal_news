# new_portal_news
News portal

The application is a news portal. The following technologies were used: Servlets, Java Server Pages (JSP), Java Server Pages Standard Tag Library (JSTL), Expression Language, The Java Database Connectivity API (JDBC).

The actions that can be performed on the site depend on the user's role (administrator, user, guest). Access to the execution of any operation is regulated by a filter (SecurityFilter). The application has a listener to initialize the connection pool.

The application interface supports English and Russian localization. The main page displays the last 5 news, there are buttons for registration, authorization, changing the locale.

When trying to perform any action, a user with the "guest" role will be directed to the authorization page. Upon successful authorization, the user can enter his personal account.

The following functionality has been implemented:

- viewing all news (paging applied - 5 news on one page);
- offer news (for the user);
- add news (only for administrator);
- add to favourites;
- remove from favorites;
- read the news;
- edit and delete news (only for the administrator);
- change password;
- approve / reject publication (only for administrator);
- view the status of the proposed news (for the user);
- comment on the news.
