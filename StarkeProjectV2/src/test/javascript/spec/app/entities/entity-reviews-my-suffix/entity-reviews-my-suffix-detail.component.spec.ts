/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityReviewsMySuffixDetailComponent } from 'app/entities/entity-reviews-my-suffix/entity-reviews-my-suffix-detail.component';
import { EntityReviewsMySuffix } from 'app/shared/model/entity-reviews-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityReviewsMySuffix Management Detail Component', () => {
        let comp: EntityReviewsMySuffixDetailComponent;
        let fixture: ComponentFixture<EntityReviewsMySuffixDetailComponent>;
        const route = ({ data: of({ entityReviews: new EntityReviewsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityReviewsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EntityReviewsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityReviewsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.entityReviews).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
