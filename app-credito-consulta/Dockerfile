# Stage 1: build Angular
FROM node:18-alpine AS builder
WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build -- --configuration production

# Stage 2: serve com Nginx
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

# Copia o build correto (pasta browser)
COPY --from=builder /app/dist/app-credito-consulta/browser/ ./

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
