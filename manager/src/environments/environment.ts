export const environment = {
  grant_type: {
    code: 'authorization_code',
    refresh_token: 'refresh_token',
  },
  authUrl: "http://localhost:8080",
  client_id: "game_master_manager",
  client_secret: "game_master_manager_secret",
  redirect_uri: "http://localhost:4200/auth",
  scope: "read write",
};
