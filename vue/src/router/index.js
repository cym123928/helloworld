import { createRouter, createWebHistory } from 'vue-router'
import SignIn from "@/components/SignIn";
import Login from "@/components/Login";
import recommendForPatient from "@/components/recommendForPatient";
import speechPatient from "@/components/speechPatient";
import recommendForDoctor from "@/components/recommendForDoctor";
import details from "@/components/details";
import pM from "@/components/pM";
import question from "@/components/question"
import rDetail from "@/components/rDetail";
import rQuestion from "@/components/rQuestion";
import collectP from "@/components/collectP";
import postedP from "@/components/postedP";
import dM from "@/components/dM";
import doctorP from "@/components/doctorP";
import sellerM from "../components/sellerM";
import finishedD from "../components/finishedD";
import unReceiveD from "../components/unReceiveD";
import unReceiveDetail from "../components/unReceiveDetail";
import myOrdered from "../components/myOrdered";
import myWillOrder from "../components/myWillOrder";
import waitComment from "../components/waitComment";
import checkComment from "../components/checkComment";
import commentD from "../components/commentD";
import checkCommentR from "../components/checkCommentR";
import CommentRes from "../components/CommentRes";
import receivedOrderD from "../components/receivedOrderD";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/SignIn',
    name: 'SignIn',
    component: SignIn
  },
  {
    path: '/pM',
    name: 'pM',
    component: pM,
    children: [
      {
        path:'/postedP',
        name:'postedP',
        component: postedP
      },
      {
        path: '/recommendForPatient',
        name: 'recommendForPatient',
        component: recommendForPatient
      },
      {
        path:'/myOrdered',
        name:'myOrdered',
        component: myOrdered
      },

      {
        path:'/myWillOrder',
        name:'myWillOrder',
        component: myWillOrder
      },

      {
        path:'/waitComment',
        name:'waitComment',
        component: waitComment
      },

      {
        path:'/checkComment',
        name:'checkComment',
        component: checkComment
      },

      {
        path: '/rQuestion',
        name: 'rQuestion',
        component: rQuestion
      },
      {
        path: '/speechPatient',
        name: 'speechPatient',
        component: speechPatient
      },
      {
        path:'/collectP',
        name:'collectP',
        component: collectP
      }
    ]
  },
  {
    path: '/sellerM',
    name: 'sellerM',
    component: sellerM,
    children: [
      {
        path: '/finishedD',
        name: 'finishedD',
        component: finishedD
      },
      {
        path: '/unReceiveD',
        name: 'unReceiveD',
        component:unReceiveD
      },
      {
        path: '/unReceiveDetail',
        name: 'unReceiveDetail',
        component:unReceiveDetail
      },
      {
        path: '/receivedOrderD',
        name: 'receivedOrderD',
        component:receivedOrderD
      },
      {
        path: '/commentD',
        name: 'commentD',
        component:commentD
      },
        ]
  },


  {
    path: '/dM',
    name: 'dM',
    component: dM,
    children: [
      {
        path: '/recommendForDoctor',
        name: 'recommendForDoctor',
        component: recommendForDoctor
      },
      {
        path: '/question',
        name: 'question',
        component:question
      },
      {
        path: '/doctorP',
        name: 'doctorP',
        component:doctorP
      },
      {
        path:'/details',
        name: 'details',
        component: details
      },
      {
        path:'/checkCommentR',
        name: 'checkCommentR',
        component: checkCommentR
      },
      {
        path:'/CommentRes',
        name: 'CommentRes',
        component: CommentRes
      },
      {
        path:'/rDetail',
        name:'rDetail',
        component: rDetail
      },
    ]
  },


]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
