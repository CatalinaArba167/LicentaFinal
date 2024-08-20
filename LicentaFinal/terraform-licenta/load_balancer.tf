resource "aws_security_group" "terraform_online_shop_load_balancer_security_group" {
  name        = "terraform_online_shop_load_balancer_security_group"
  description = "Security group for online shop load balancer"
  vpc_id      = aws_vpc.terraform_vpc.id 

  tags = {
    Name = "terraform_online_shop_load_balancer_security_group"
  }
}

resource "aws_vpc_security_group_ingress_rule" "allow_tcp_80" {
  security_group_id            = aws_security_group.terraform_online_shop_load_balancer_security_group.id
  cidr_ipv4                    = "0.0.0.0/0"
  from_port                    = 80
  ip_protocol                  = "tcp"
  to_port                      = 80
}
resource "aws_vpc_security_group_ingress_rule" "allow_tcp_8080" {
  security_group_id            = aws_security_group.terraform_online_shop_load_balancer_security_group.id
  cidr_ipv4                    = "0.0.0.0/0"
  from_port                    = 8080
  ip_protocol                  = "tcp"
  to_port                      = 8080
}
resource "aws_vpc_security_group_ingress_rule" "allow_tcp_5000" {
  security_group_id            = aws_security_group.terraform_online_shop_load_balancer_security_group.id
  cidr_ipv4                    = "0.0.0.0/0"
  from_port                    = 5000
  ip_protocol                  = "tcp"
  to_port                      = 5000
}
resource "aws_vpc_security_group_egress_rule" "allow_all_traffic_ipv4_for_lb" {
  security_group_id = aws_security_group.terraform_online_shop_load_balancer_security_group.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
}


resource "aws_lb" "terraform_load_balancer" {
  name               = "TerraformLoadBalancer"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.terraform_online_shop_load_balancer_security_group.id]
  subnets            = [aws_subnet.public_subnet_1.id, aws_subnet.public_subnet_2.id]

  enable_deletion_protection = false
  
  lifecycle {
    #prevent_destroy = true
  }

}

resource "aws_lb_target_group" "terraform_target_group_backend" {
  name     = "TerraformTargetGroupBackend"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_vpc.terraform_vpc.id
  target_type = "instance"

  health_check {
    enabled             = true
    interval            = 30
    path                = "/api/health"
    protocol            = "HTTP"
    matcher             = "200"
  }
}

resource "aws_lb_target_group" "terraform_target_group_ai" {
  name     = "TerraformTargetGroupAi"
  port     = 5000
  protocol = "HTTP"
  vpc_id   = aws_vpc.terraform_vpc.id
  target_type = "instance"

  health_check {
    enabled             = true
    interval            = 30
    path                = "/health"
    protocol            = "HTTP"
    matcher             = "200"
  }
}

# resource "aws_lb_listener" "terraform_listener" {
#   load_balancer_arn = aws_lb.terraform_load_balancer.arn
#   port              = 80
#   protocol          = "HTTP"

#   default_action {
#     type             = "forward"
#     target_group_arn = aws_lb_target_group.terraform_target_group.arn
#   }
# }


# resource "aws_lb_target_group" "terraform_target_group_for_ecs" {
#   name     = "TerraformTargetGroupForECS"
#   port     = 8080
#   protocol = "HTTP"
#   vpc_id   = aws_vpc.terraform_vpc.id
#   target_type = "ip"
#   health_check {
#     enabled             = true
#     interval            = 30
#     path                = "/health"
#     protocol            = "HTTP"
#     matcher             = "200"
#   }
# }

resource "aws_lb_listener" "terraform_listener_for_ecs_ai" {
  load_balancer_arn = aws_lb.terraform_load_balancer.arn
  port              = 5000
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.terraform_target_group_ai.arn
  }
}

resource "aws_lb_listener" "terraform_listener_for_ecs_backend" {
  load_balancer_arn = aws_lb.terraform_load_balancer.arn
  port              = 8080
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.terraform_target_group_backend.arn
  }
}