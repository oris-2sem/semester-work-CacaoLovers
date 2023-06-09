
import './App.css';
import {Route, Routes} from "react-router";
import {Home} from "./pages/Home/Home";
import {Missing} from "./pages/Missing/Missing";
import React from "react";
import {Shelter} from "./pages/Shelter/Shelter";
import {PageNotFound} from "./pages/Error/PageNotFound";
import {Layout} from "./components/Layout/Layout";
import {Volunteer} from "./pages/Volunteer/Volunteer";
import {Profile} from "./pages/Profile/Profile";
import {ProfileInfo} from "./pages/Profile/ProfileInfo/ProfileInfo";
import {MissingProfile} from "./pages/Profile/MissingProfile/MisingProfile";
import {NotificationProfile} from "./pages/Profile/NotificationProfile/NotificationProfile";
import {MissingCreate} from "./pages/MisssingCreate/MissingCreate";
import {useAccount} from "./store/AccountProvider";
import {MissingInfo} from "./pages/MisssingCreate/components/MissingInfo";
import {MapLocation} from "./pages/MisssingCreate/components/MapLocation";
import {MissingPhoto} from "./pages/MisssingCreate/components/MissingPhoto";
import {MissingVerify} from "./pages/MisssingCreate/components/MissingVerify";
import {MissingContainer} from "./pages/Missing/MissingContainer";

function App() {
    const {auth} = useAccount()
  return(
      <Routes>
          <Route path="/" element={<Layout/>}>
            <Route element={<Home title={'Главная'}/>} index/>
              <Route path="/auth" element={<Home title={'Главная'} auth={auth}/> } />
              <Route  path="/missing" element={<MissingContainer/>}>
                  <Route  path="/missing/lost" element={<Missing title={'Объявления о пропаже'}/>} index/>
                  <Route  path="/missing/found" element={<Missing title={'Объявления о находке'}/>}/>
              </Route>
            <Route path="/volunteer/*" element={<Volunteer title={'Волонтерство'}/>}/>
            <Route path="/shelter" element={<Shelter title={'Приюты'}/>}/>
            <Route path="/profile" element={<Profile title={'Профиль'}/>}>
                <Route path={"/profile/:userId/info"} element={<ProfileInfo/>}/>
                <Route path={"/profile/:userId/missing"} element={<MissingProfile/>}/>
                <Route path={"/profile/:userId/notification"} element={<NotificationProfile/>}/>
            </Route>
            <Route path="*" element={<PageNotFound title={'Страница не найдена'}/>}/>
              <Route path={"/missing/create/:type"} element={<MissingCreate title={'Создание объявления'}/>}>
                  <Route path={"/missing/create/:type/info"} element={<MissingInfo/>} index/>
                  <Route path={"/missing/create/:type/location"} element={<MapLocation/>}/>
                  <Route path={"/missing/create/:type/photo"} element={<MissingPhoto/>}/>
                  <Route path={"/missing/create/:type/verify"} element={<MissingVerify/>}/>
              </Route>
          </Route>
      </Routes>
  )
}

export default App;
