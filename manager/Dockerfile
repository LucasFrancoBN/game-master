# Stage 1: Build the Angular application
FROM node:alpine AS builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Stage 2: Serve the application using Nginx
FROM nginx:alpine

COPY --from=builder /app/dist/manager/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
COPY mime.types /etc/nginx/mime.types

EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
