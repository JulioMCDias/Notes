package com.jlmcdeveloper.notes.ui.login;

import com.jlmcdeveloper.notes.RxImmediateSchedulerRule;
import com.jlmcdeveloper.notes.data.DataManager;
import com.jlmcdeveloper.notes.data.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock LoginMvpView mockLoginMvpView;
    @Mock DataManager mockDataManager;
    private LoginPresenter<LoginMvpView> loginPresenter;
    private String name= "test";
    private String password = "test123";

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        loginPresenter = new LoginPresenter<>(mockDataManager, compositeDisposable);
        loginPresenter.onAttach(mockLoginMvpView);

    }

    @After
    public void tearDown() throws Exception {
        loginPresenter.onDetach();
    }



    @Test
    public void testLoginRemoteSuccess() {
        User user = new User(10L, name, "test@test", password);

        // remoto
        doReturn(Single.just(user))
                .when(mockDataManager)
                .setLoginRemote(any(User.class));

        // local criar
        doReturn(Completable.complete())
                .when(mockDataManager)
                .createLoginLocal(any(User.class));

        loginPresenter.setUser(name, password);

        verify(mockLoginMvpView).showLoading();
        verify(mockDataManager).setUser(user);
        verify(mockLoginMvpView).hideLoading();
        verify(mockLoginMvpView).openMainActivity();
    }


    @Test
    public void testLoginLocalSuccess() {
        User user = new User(2L, name, "test@test", password);

        // remoto
        doReturn(Single.just(new User(-1L, name, "test@test", password)))
                .when(mockDataManager)
                .setLoginRemote(any(User.class));

        // local loga
        doReturn(Single.just(user))
                .when(mockDataManager)
                .setLoginLocal(any(User.class));

        // local criar
        doReturn(Completable.complete())
                .when(mockDataManager)
                .createLoginLocal(any(User.class));

        // remoto criar
        doReturn(Single.just(user))
                .when(mockDataManager)
                .createLoginRemote(any(User.class));

        loginPresenter.setUser(name, password);

        verify(mockLoginMvpView, times(2)).showLoading();
        verify(mockLoginMvpView, times(2)).hideLoading();
        verify(mockLoginMvpView).openMainActivity();
        verify(mockDataManager).setUser(user);
    }


    @Test
    public void testLoginLocalErrorDatabase(){
        // remoto
        doReturn(Single.error(new Throwable()))
                .when(mockDataManager)
                .setLoginRemote(any(User.class));

        // local loga
        doReturn(Single.error(new Throwable()))
                .when(mockDataManager)
                .setLoginLocal(any(User.class));


        loginPresenter.setUser(name, password);

        verify(mockLoginMvpView).showLoading();
        verify(mockLoginMvpView).finishedAnimation("erro no login");
    }



    @Test
    public void testCreateUserSuccess(){
        User user = new User(2L, name, "test@test", password);

        // create remote
        doReturn(Single.just(user))
                .when(mockDataManager)
                .createLoginRemote(any(User.class));

        // create local
        doReturn(Completable.complete())
                .when(mockDataManager)
                .createLoginLocal(any(User.class));

        loginPresenter.createUser(name, password);

        verify(mockLoginMvpView).showLoading();
        verify(mockLoginMvpView).hideLoading();
    }


    @Test
    public void testCreateUserErrorDatabase(){
        User user = new User(-1L, name, "test@test", password);

        // create remote
        doReturn(Single.just(user))
                .when(mockDataManager)
                .createLoginRemote(any(User.class));

        // create local
        doReturn(Completable.complete())
                .when(mockDataManager)
                .createLoginLocal(any(User.class));

        loginPresenter.createUser(name, password);

        verify(mockLoginMvpView).showLoading();
        verify(mockLoginMvpView).finishedAnimation("erro no login");
    }

    @Test
    public void testCreateUserErrorNetwork(){
        // create remote
        doReturn(Single.error(new Throwable()))
                .when(mockDataManager)
                .createLoginRemote(any(User.class));

        // create local
        doReturn(Completable.complete())
                .when(mockDataManager)
                .createLoginLocal(any(User.class));

        loginPresenter.createUser(name, password);

        verify(mockLoginMvpView).showLoading();
        verify(mockLoginMvpView).finishedAnimation("Erro na comunicação");
    }
}