import { getSpot, modifyContent, search } from '~/api/spot'

export const state = () => ({
  query: '',
  keyword: [],
  spotList: [],
  spot: null,
  page: 0,
})

export const mutations = {
  SET_QUERY(state, query) {
    state.query = query
  },
  SET_KEYWORD(state, keyword) {
    state.keyword = keyword
  },

  SET_SPOTLIST(state, spotList) {
    state.spotList = spotList
  },
  SET_SPOT(state, spot) {
    state.spot = spot
  },
  ADD_SPOTLIST(state, spotList) {
    spotList.forEach((spot) => {
      state.spotList.content.push(spot)
    })
  },
  ADD_PAGE(state) {
    state.page += 1
  },
  CLEAR_PAGE(state) {
    state.page = 0
  },
  CLEAR_QUERY(state) {
    state.query = ''
  },
  CLEAR_SPOTLIST(state) {
    state.spotList = []
  },
  CLEAR_SPOT(state) {
    state.spot = null
  },
  MODIFY_CONTENT(state, content) {
    state.spot.content = content
  },
}

export const getters = {}

export const actions = {
  callSpot({ commit }, spotId) {
    getSpot(
      spotId,
      ({ data }) => {
        commit('SET_SPOT', data)
      },
      (error) => {
        console.log(error)
      }
    )
  },

  changeContent({ state, commit }, content) {
    modifyContent(
      state.spot.spotId,
      content,
      ({ data }) => {
        commit('MODIFY_CONTENT', content)
      },
      (error) => {
        console.log(error)
      }
    )
  },

  async searchSpot({ commit }, query) {
    await search(
      query,
      ({ data }) => {
        commit('SET_SPOTLIST', data)
      },
      (error) => {
        console.log(error)
      }
    )
  },
  addSpot({ commit }, query) {
    search(
      query,
      ({ data }) => {
        commit('ADD_SPOTLIST', data.content)
      },
      (error) => {
        console.log(error)
      }
    )
  },

  // 필요한 것들
  // 1. 선택된 spot 불러오기
  // 2. 선택된 spot의 리뷰 불러오기
}
