resource "aws_ecs_cluster" "terraform_ecs_cluster" {
  name = "terraform_ecs_cluster"
  configuration {
    execute_command_configuration {
      logging = "OVERRIDE"
      log_configuration {
        cloud_watch_log_group_name = aws_cloudwatch_log_group.ecs_logs_backend.name
      }
    }
  }
}

resource "aws_cloudwatch_log_group" "ecs_logs_backend" {
  name = "/ecs/online-shop-task_backend"
}

resource "aws_cloudwatch_log_group" "ecs_logs_ai" {
  name = "/ecs/online-shop-task_ai"
}



resource "aws_ecs_task_definition" "terraform-ecs-task" {
  family                   = "online-shop-task"
  requires_compatibilities = ["EC2"]
  network_mode             = "bridge"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn


  container_definitions = jsonencode([{
    name         = "my-backend-container",
    image        = "${aws_ecr_repository.terraform_ecr_repo_baclend.repository_url}:${var.docker_image_tag}",
    essential    = true,
    portMappings = [{
      containerPort = 8080,
      hostPort      = 8080
    }],
     logConfiguration = {
      logDriver = "awslogs",
      options = {
        "awslogs-group"         = aws_cloudwatch_log_group.ecs_logs_backend.name,
        "awslogs-region"        = "us-east-1",
        "awslogs-stream-prefix" = "ecs"
      }
    }
    environment  = [
      { name = "SPRING_DATASOURCE_USERNAME", value = "postgres" },
      { name = "SPRING_DATASOURCE_URL", value = local.rds_endpoint },
      { name = "SPRING_DATASOURCE_PASSWORD", value = "postgres" },
      { name = "SPRING_REDIS_HOST", value = local.radis_endpoint },  
      { name = "SPRING_REDIS_PORT", value = "6379" },
      { name = "SPRING_SESSION_STORETYPE", value = "redis" },
      { name = "SPRING_SESSION_REDIS_CONFIGUREACTION", value = "none" },
      {name= "SPRING_FLYWAY_USER",value= "postgres"},
      {name= "SPRING_FLYWAY_PASSWORD",value= "postgres"},
      {name="SPRING_FLYWAY_URL", value= local.rds_endpoint}
    ],
    cpu          = 512 
    memory       = 1024  
  },
  

  {
    name         = "my-ai-container",
    image        = "${aws_ecr_repository.terraform_ecr_repo_ai.repository_url}:${var.docker_image_tag}",
    essential    = true,
    portMappings = [{
      containerPort = 5000,
      hostPort      = 5000
    }],
     logConfiguration = {
      logDriver = "awslogs",
      options = {
        "awslogs-group"         = aws_cloudwatch_log_group.ecs_logs_ai.name,
        "awslogs-region"        = "us-east-1",
        "awslogs-stream-prefix" = "ecs"
      }
    }
    cpu          = 512 
    memory       = 768 
  }
  ])
}





resource "aws_ecs_service" "terraform_ecs_service" {
  name="terraform_ecs_service"
  cluster = aws_ecs_cluster.terraform_ecs_cluster.id
  task_definition = aws_ecs_task_definition.terraform-ecs-task.arn
  desired_count = 1
  force_new_deployment = true

  
  load_balancer {
    target_group_arn = aws_lb_target_group.terraform_target_group_ai.arn
    container_name = "my-ai-container"
    container_port = 5000
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.terraform_target_group_backend.arn
    container_name = "my-backend-container"
    container_port = 8080
  }

  #  network_configuration {
  #   subnets = [aws_subnet.private_subnet_1.id,aws_subnet.private_subnet_2.id,aws_subnet.public_subnet_1.id, aws_subnet.public_subnet_2.id]
  #   #security_groups = [ aws_security_group.terraform_online_shop_ecs_security_group.id]
  #  }
   
   capacity_provider_strategy {
     capacity_provider = aws_ecs_capacity_provider.ecs_capacity_provider.name
     weight = 100
   }
}


resource "aws_ecs_capacity_provider" "ecs_capacity_provider" {
  name = "shop-ecs-capacity-provider"

  auto_scaling_group_provider {
    auto_scaling_group_arn = aws_autoscaling_group.terraform_auto_scaling_group.arn

    managed_termination_protection = "DISABLED"

    managed_scaling {
      maximum_scaling_step_size = 2
      minimum_scaling_step_size = 1
      status                    = "ENABLED"
      target_capacity           = 100
    }
  }
}


resource "aws_ecs_cluster_capacity_providers" "main" {
  cluster_name = aws_ecs_cluster.terraform_ecs_cluster.name

  capacity_providers = [aws_ecs_capacity_provider.ecs_capacity_provider.name]

  default_capacity_provider_strategy {
    base              = 1
    weight            = 100
    capacity_provider = aws_ecs_capacity_provider.ecs_capacity_provider.name
  }
}